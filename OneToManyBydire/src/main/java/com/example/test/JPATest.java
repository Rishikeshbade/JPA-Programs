package com.example.test;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.example.entity.Option;
import com.example.entity.Question;

public class JPATest {

	private static EntityManagerFactory entityManagerFactory;
	private static EntityManager entityManager;
	
    

	public static Question createQuestion(String questionText) {
		entityManager.getTransaction().begin();
		Question question = new Question();
		question.setQuestion(questionText);
		entityManager.persist(question);
		entityManager.getTransaction().commit();
		return question;

	}

	public static Option createOption(String optionText, Question question) {
		entityManager.getTransaction().begin();
		Option option = new Option();
		option.setOptionText(optionText);
		option.setQuestion(question);
		entityManager.persist(option);
		entityManager.getTransaction().commit();
		return option;
	}

	public static Question findQuestionById(long questionId) {
		return entityManager.find(Question.class, questionId);

	}

	public static void updateQuestionText(Question question, String newQuestionText) {
		entityManager.getTransaction().begin();
		question.setQuestion(newQuestionText);
		entityManager.getTransaction().commit();
	}

	public static void updateOptionText(Option option, String newOptionText) {
		
		entityManager.getTransaction().begin();
		option.setOptionText(newOptionText);
		option.setOptionText(newOptionText);
		entityManager.getTransaction().commit();
	}

	public static void deleteQuestion(Question question) {
		entityManager.getTransaction().begin();
		entityManager.remove(question);
		entityManager.getTransaction().commit();
	}

	public static List<Question> findAllQuestion() {
		TypedQuery<Question> query = entityManager.createQuery("SELECT q FROM Question q", Question.class);
		return query.getResultList();
	}

	public static void main(String[] args) {
		entityManagerFactory = Persistence.createEntityManagerFactory("pu");
		entityManager = entityManagerFactory.createEntityManager();
		
		
		
		
		Scanner sc = new Scanner(System.in);
		int choice;
		do {
			System.out.println("Choose an Option");
			System.out.println("1. Create Question and Options");
			System.out.println("2. Fetched Question");
			System.out.println("3. Update Question And Options");
			System.out.println("4. Delete Question");
			System.out.println("5. Fetched All Question ");
			System.out.println("6. Exit");
			System.out.println("Enter Your Choice");
			choice = sc.nextInt();
			
			switch (choice) {
			
            case 1:
                System.out.println("Enter Your Question : ");
                String questionText = sc.nextLine();
                Question question = createQuestion(questionText);
                
                System.out.println("Enter Option 1 : ");
                String optiontext1 = sc.nextLine();
                Option option1 = createOption(optiontext1, question);
                
                System.out.println("Enter Option 2 : ");
                String optiontext2 = sc.nextLine();
                Option option2 = createOption(optiontext2, question);

                System.out.println("Question and Options Created");
                System.out.println("Question : " + question.getQuestion());
                System.out.println("Options : ");
                for (Option option : question.getOptions()) {
                    System.out.println(option.getOptionText());
                }
                break;
            case 2:
                System.out.println("Enter Question Id to Fetch: ");
                long id = sc.nextLong();
                Question fetchedQuestion = findQuestionById(id);
                if (fetchedQuestion != null) {
                    System.out.println("Fetched Question : " + fetchedQuestion.getQuestion());
                } else {
                    System.out.println("Question not found.");
                }
                break;
            case 3:
            	System.out.println("Enter Question Id to Update: ");
                long questionIdToUpdate = sc.nextLong();
                Question questionToUpdate = findQuestionById(questionIdToUpdate);
                if (questionToUpdate != null) {
                    updateQuestionText(questionToUpdate, "What is your favorite Colour ?");
                    System.out.println("Updated Question Text : " + questionToUpdate.getQuestion());
                    
                    Option option11 = questionToUpdate.getOptions().get(0);
                    Option option21 = questionToUpdate.getOptions().get(1);

                    updateOptionText(option11, "Red");
                    updateOptionText(option21, "Blue");
                    System.out.println("Updated Option Text : " + option11.getOptionText());
                    System.out.println("Updated Option Text : " + option21.getOptionText());
                }
                break;
            case 4:
                System.out.println("Enter Question Id to Delete: ");
                long questionIdToDelete = sc.nextLong();
                Question questionToDelete = findQuestionById(questionIdToDelete);
                if (questionToDelete != null) {
                    deleteQuestion(questionToDelete);
                    System.out.println("Question Deleted Successfully.");
                } else {
                    System.out.println("Question not found.");
                }
                break;
            case 5:
                List<Question> allQuestions = findAllQuestion();
                if (allQuestions.isEmpty()) {
                    System.out.println("No questions found.");
                } else {
                    System.out.println("All Questions: ");
                    for (Question q : allQuestions) {
                        System.out.println(q.getQuestion());
                    }
                }
                break;
            case 6:
                System.out.println("Exiting....");
                break;
            default:
                System.out.println("Invalid choice");
        }

    } while(choice != 6);
	
		
	}

}
