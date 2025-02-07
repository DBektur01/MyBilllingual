package javaB13.repositories.custom.impl;

import  javaB13.dto.responses.answer.UserAnswerResponse;
import  javaB13.dto.responses.file.FileResponse;
import  javaB13.dto.responses.option.OptionResponse;
import  javaB13.dto.responses.questions.EvaluateQuestionResponse;
import  javaB13.dto.responses.questions.QuestionResponse;
import  javaB13.entity.Answer;
import  javaB13.enums.FileType;
import  javaB13.enums.QuestionType;
import  javaB13.exceptions.NotFoundException;
import  javaB13.repositories.AnswerRepository;
import  javaB13.repositories.custom.CustomAnswerRepository;
import  javaB13.repositories.custom.CustomQuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class CustomQuestionRepositoryImpl implements CustomQuestionRepository {
    private final JdbcTemplate jdbcTemplate;
    private final CustomAnswerRepository customAnswerRepository;
    private final AnswerRepository answerRepository;

    @Override
    public List<QuestionResponse> getAllQuestions() {
        String sql = """
                SELECT
                q.id as id,
                q.title as title,
                q.statement as statement,
                q.question_type as question_type,
                q.duration as duration,
                q.min_words as min_words,
                q.number_of_replays as number_of_replays,
                q.correct_answer as correct_answer,
                q.passage as passage,
                q.question_order as question_order,
                q.is_active as is_active,
                q.audio_text as audio_text,
                t.id as test_id
                FROM questions q join tests t on t.id = q.test_id
                """;

        String fileQuery = """
                SELECT
                f.id as fileId,
                f.file_type as fileType,
                f.file_url as fileUrl,
                f.question_id as questionId
                FROM files f
                """;

        List<FileResponse> files = jdbcTemplate.query(fileQuery, (resultSet, i) ->
                new FileResponse(
                        resultSet.getLong("fileId"),
                        FileType.valueOf(resultSet.getString("fileType")),
                        resultSet.getString("fileUrl"),
                        resultSet.getLong("questionId")
                ));

        List<QuestionResponse> questions = jdbcTemplate.query(sql, (resultSet, i) ->
                new QuestionResponse(
                        resultSet.getLong("id"),
                        resultSet.getString("title"),
                        resultSet.getString("statement"),
                        QuestionType.valueOf(resultSet.getString("question_type")),
                        resultSet.getInt("duration"),
                        resultSet.getInt("min_words"),
                        resultSet.getInt("number_of_replays"),
                        resultSet.getString("correct_answer"),
                        resultSet.getString("passage"),
                        resultSet.getInt("question_order"),
                        resultSet.getString("audio_text"),
                        resultSet.getLong("test_id"),
                        null,
                        null,
                        resultSet.getBoolean("is_active")
                ));


        String optionQuery = """
                SELECT
                 o.id as id,
                 o.question_id as questionId,
                 o.file_url as fileUrl,
                 o.title as title,
                 o.option_order as option_order,
                 o.is_correct as isCorrect
                 FROM options o
                 """;


        List<OptionResponse> options = jdbcTemplate.query(optionQuery, (resultSet, i) ->

                new OptionResponse(
                        resultSet.getLong("id"),
                        resultSet.getString("title"),
                        resultSet.getBoolean("isCorrect"),
                        resultSet.getLong("questionId"),
                        resultSet.getString("fileUrl"),
                        resultSet.getInt("option_order")
                ));

        // TODO Inserting files & options to related questions
        questions.forEach(question -> {
            List<FileResponse> fileResponseList = files.stream()
                    .filter(file -> file.getQuestionId().equals(question.getId()))
                    .collect(Collectors.toList());
            List<OptionResponse> optionResponseList = options.stream()
                    .filter(option -> option.getQuestionId().equals(question.getId()))
                    .collect(Collectors.toList());
            question.setOptions(optionResponseList);
            question.setFiles(fileResponseList);
        });

        return questions;
    }

    @Override
    public Optional<QuestionResponse> getQuestionById(Long id) {


        String fileSql = """
                SELECT
                f.id as file_id,
                f.file_type as file_type,
                f.file_url as file_url,
                f.question_id as question_id
                FROM files f WHERE f.question_id = ?
                """;

        List<FileResponse> fileResponses = jdbcTemplate.query(fileSql, (resultSet, i) ->
                new FileResponse(
                        resultSet.getLong("file_id"),
                        FileType.valueOf(resultSet.getString("file_type")),
                        resultSet.getString("file_url"),
                        resultSet.getLong("question_id")
                ), id);

        String optionsQuery = """
                SELECT
                o.id as id,
                o.question_id as questionId,
                o.file_url as fileUrl,
                o.title as title,
                o.option_order as option_order,
                o.is_correct as isCorrect
                FROM options o WHERE o.question_id = ?
                """;

        List<OptionResponse> options = jdbcTemplate.query(optionsQuery, (resultSet, i) ->
                new OptionResponse(
                        resultSet.getLong("id"),
                        resultSet.getString("title"),
                        resultSet.getBoolean("isCorrect"),
                        resultSet.getLong("questionId"),
                        resultSet.getString("fileUrl"),
                        resultSet.getInt("option_order")
                ), id);

        String sql = """
                SELECT
                q.id as id,
                q.title as title,
                q.statement as statement,
                q.question_type as question_type,
                q.duration as duration,
                q.min_words as min_words,
                q.number_of_replays as number_of_replays,
                q.correct_answer as correct_answer,
                q.passage as passage,
                q.question_order as question_order,
                q.audio_text as audio_text,
                q.is_active as is_active,
                t.id as test_id
                FROM questions q join tests t on t.id = q.test_id
                where q.id = ?
                """;

        QuestionResponse response = jdbcTemplate.query(sql, (resultset, i) ->
                new QuestionResponse(
                        resultset.getLong("id"),
                        resultset.getString("title"),
                        resultset.getString("statement"),
                        QuestionType.valueOf(resultset.getString("question_type")),
                        resultset.getInt("duration"),
                        resultset.getInt("min_words"),
                        resultset.getInt("number_of_replays"),
                        resultset.getString("correct_answer"),
                        resultset.getString("passage"),
                        resultset.getInt("question_order"),
                        resultset.getString("audio_text"),
                        resultset.getLong("test_id"),
                        null,
                        null,
                        resultset.getBoolean("is_active")
                ), id).stream().findAny().orElseThrow(() -> new NotFoundException(String.format("Question with id %s was not found", id)));

        response.setFiles(fileResponses);
        response.setOptions(options);
        return Optional.of(response);
    }

    @Override
    public EvaluateQuestionResponse getEvaluateQuestionByIdes(Long answerId, Long questionId) {

        String sql = """
                SELECT concat(u.first_name, ' ', u.last_name) as full_name,
                t.title as title,
                a.evaluated_score as score,
                a.id as answer_id
                FROM answers a
                JOIN users u on a.user_id = u.id
                JOIN questions q on a.question_id = q.id
                JOIN tests t on q.test_id = t.id
                WHERE a.id = ?
                """;

        Optional<QuestionResponse> questionById = getQuestionById(questionId);

        Answer answer = answerRepository.findById(answerId).orElseThrow(() -> new NotFoundException(String.format("Answer with id %s was not found", answerId)));

        List<UserAnswerResponse> answerResponses = customAnswerRepository.getAnswerResponsesByQuestionId(questionId, answer.getUser().getId());

        for (UserAnswerResponse answerRespons : answerResponses) {
            answerRespons.setFileUrl(getByAnswerId(answerRespons.getAnswerId()));

            System.err.println(answerRespons.getAnswerId());

        }

        EvaluateQuestionResponse evaluateQuestionResponses = jdbcTemplate.query(sql, (resultSet, i) ->
                        new EvaluateQuestionResponse(
                                resultSet.getString("full_name"),
                                resultSet.getString("title"),
                                resultSet.getFloat("score"),
                                resultSet.getLong("answer_id")
                        ),
                answerId
        ).stream().findFirst().orElseThrow(()-> new NotFoundException("first index is empty"));

        evaluateQuestionResponses.setQuestionResponse(questionById.orElseThrow(()-> new NotFoundException(String.format("question with id : %s not found", questionId))));
        evaluateQuestionResponses.setUserAnswerResponse(answerResponses);
        return evaluateQuestionResponses;
    }

    private String getByAnswerId(Long answerId) {
        String sql = """
            SELECT
            f.file_url as file_url
            FROM answers a
            JOIN answers_files af on a.id = af.answer_id
            JOIN files f on af.files_id = f.id
            WHERE a.id = ?
            """;

        String file_url = jdbcTemplate.query(sql, (resultSet) -> {
            if (resultSet.next()) {
                return resultSet.getString("file_url");
            }
            return null; // Return null if no result is found
        }, answerId);
        System.err.println(file_url + " " + answerId);
        return file_url;
    }

}
