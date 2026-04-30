package com.constitutional360.config;

import com.constitutional360.entity.*;
import com.constitutional360.repository.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataSeeder implements CommandLineRunner {

    private final ArticleRepository articleRepository;
    private final AmendmentRepository amendmentRepository;
    private final QuizRepository quizRepository;
    private final QuestionRepository questionRepository;
    private final RightRepository rightRepository;
    private final FlashcardRepository flashcardRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ObjectMapper objectMapper;

    @Override
    public void run(String... args) throws Exception {
        if (articleRepository.count() == 0) {
            seedArticles();
            log.info("Seeded articles");
        }
        if (amendmentRepository.count() == 0) {
            seedAmendments();
            log.info("Seeded amendments");
        }
        if (quizRepository.count() == 0) {
            seedQuizzes();
            log.info("Seeded quizzes");
        }
        if (rightRepository.count() == 0) {
            seedRights();
            log.info("Seeded rights/scenarios");
        }
        if (flashcardRepository.count() == 0) {
            seedFlashcards();
            log.info("Seeded flashcards");
        }
        if (userRepository.count() == 0) {
            seedUsers();
            log.info("Seeded demo users");
        }
    }

    private void seedArticles() {
        List<Article> articles = List.of(
            Article.builder().part("Preamble").title("Preamble to the Constitution").articleNumber("Preamble")
                .text("WE, THE PEOPLE OF INDIA, having solemnly resolved to constitute India into a SOVEREIGN SOCIALIST SECULAR DEMOCRATIC REPUBLIC and to secure to all its citizens: JUSTICE, social, economic and political; LIBERTY of thought, expression, belief, faith and worship; EQUALITY of status and of opportunity; and to promote among them all FRATERNITY assuring the dignity of the individual and the unity and integrity of the Nation.")
                .simplified("The Preamble is like a promise made by the people of India to themselves. It says India will be a free, fair country where everyone gets justice, freedom, equality, and a sense of brotherhood.")
                .category("Preamble").importance("high").build(),
            Article.builder().part("Part III").title("Right to Equality").articleNumber("Article 14")
                .text("The State shall not deny to any person equality before the law or the equal protection of the laws within the territory of India.")
                .simplified("Everyone is equal in the eyes of the law. The government cannot treat anyone differently based on who they are.")
                .category("Fundamental Rights").importance("high").build(),
            Article.builder().part("Part III").title("Prohibition of Discrimination").articleNumber("Article 15")
                .text("The State shall not discriminate against any citizen on grounds only of religion, race, caste, sex, place of birth or any of them.")
                .simplified("The government cannot discriminate against anyone because of their religion, race, caste, gender, or where they were born.")
                .category("Fundamental Rights").importance("high").build(),
            Article.builder().part("Part III").title("Equality of Opportunity").articleNumber("Article 16")
                .text("There shall be equality of opportunity for all citizens in matters relating to employment or appointment to any office under the State.")
                .simplified("Everyone should have an equal chance to get government jobs.")
                .category("Fundamental Rights").importance("high").build(),
            Article.builder().part("Part III").title("Right to Freedom").articleNumber("Article 19")
                .text("All citizens shall have the right to freedom of speech and expression; to assemble peaceably and without arms; to form associations or unions; to move freely throughout the territory of India; to reside and settle in any part of India; and to practise any profession.")
                .simplified("You have the freedom to speak your mind, gather peacefully, form groups, travel anywhere in India, live wherever you want, and choose any job or business.")
                .category("Fundamental Rights").importance("high").build(),
            Article.builder().part("Part III").title("Protection of Life and Personal Liberty").articleNumber("Article 21")
                .text("No person shall be deprived of his life or personal liberty except according to procedure established by law.")
                .simplified("No one can take away your life or freedom unless there is a proper legal process for it.")
                .category("Fundamental Rights").importance("high").build(),
            Article.builder().part("Part III").title("Right to Education").articleNumber("Article 21A")
                .text("The State shall provide free and compulsory education to all children of the age of six to fourteen years.")
                .simplified("Every child between 6 and 14 years old has the right to free education.")
                .category("Fundamental Rights").importance("high").build(),
            Article.builder().part("Part III").title("Right against Exploitation").articleNumber("Article 23")
                .text("Traffic in human beings and begar and other similar forms of forced labour are prohibited.")
                .simplified("Human trafficking and forced labour are completely banned.")
                .category("Fundamental Rights").importance("high").build(),
            Article.builder().part("Part III").title("Right to Freedom of Religion").articleNumber("Article 25")
                .text("All persons are equally entitled to freedom of conscience and the right freely to profess, practise and propagate religion.")
                .simplified("Everyone is free to follow, practice, and share their religion.")
                .category("Fundamental Rights").importance("medium").build(),
            Article.builder().part("Part III").title("Right to Constitutional Remedies").articleNumber("Article 32")
                .text("The right to move the Supreme Court by appropriate proceedings for the enforcement of the rights conferred by this Part is guaranteed.")
                .simplified("If any of your fundamental rights are violated, you can go directly to the Supreme Court to get justice.")
                .category("Fundamental Rights").importance("high").build(),
            Article.builder().part("Part IV").title("Equal Justice and Free Legal Aid").articleNumber("Article 39A")
                .text("The State shall secure that the operation of the legal system promotes justice, on a basis of equal opportunity.")
                .simplified("The government should make sure the legal system is fair for everyone and provide free legal help.")
                .category("Directive Principles").importance("medium").build(),
            Article.builder().part("Part IV").title("Organisation of Village Panchayats").articleNumber("Article 40")
                .text("The State shall take steps to organise village panchayats and endow them with such powers and authority.")
                .simplified("The government should set up village councils (Panchayats) and give them enough power.")
                .category("Directive Principles").importance("medium").build(),
            Article.builder().part("Part IV-A").title("Fundamental Duties").articleNumber("Article 51A")
                .text("It shall be the duty of every citizen of India to abide by the Constitution and respect its ideals and institutions.")
                .simplified("Every Indian citizen should respect the Constitution, honor freedom fighters' ideals, and protect India's unity.")
                .category("Fundamental Duties").importance("high").build(),
            Article.builder().part("Part V").title("The President of India").articleNumber("Article 52")
                .text("There shall be a President of India.")
                .simplified("India must have a President who is the head of state.")
                .category("Union Government").importance("medium").build(),
            Article.builder().part("Part V").title("Parliament of India").articleNumber("Article 79")
                .text("There shall be a Parliament for the Union which shall consist of the President and two Houses.")
                .simplified("India's Parliament has two houses: the Rajya Sabha and the Lok Sabha, along with the President.")
                .category("Union Government").importance("medium").build()
        );
        articleRepository.saveAll(articles);
    }

    private void seedAmendments() {
        List<Amendment> amendments = List.of(
            Amendment.builder().year(1951).number("1st").title("First Amendment").description("Added reasonable restrictions on freedom of speech. Introduced Ninth Schedule.").build(),
            Amendment.builder().year(1971).number("24th").title("Twenty-fourth Amendment").description("Affirmed Parliament's power to amend any part of the Constitution.").build(),
            Amendment.builder().year(1976).number("42nd").title("Forty-second Amendment").description("Known as the 'Mini Constitution.' Added words 'Socialist, Secular' to the Preamble. Added Fundamental Duties.").build(),
            Amendment.builder().year(1978).number("44th").title("Forty-fourth Amendment").description("Reversed several changes of the 42nd Amendment. Restored civil liberties.").build(),
            Amendment.builder().year(1985).number("52nd").title("Fifty-second Amendment").description("Anti-defection law to prevent elected members from switching parties.").build(),
            Amendment.builder().year(1992).number("73rd").title("Seventy-third Amendment").description("Gave constitutional status to Panchayati Raj institutions.").build(),
            Amendment.builder().year(1992).number("74th").title("Seventy-fourth Amendment").description("Gave constitutional status to urban local bodies (municipalities).").build(),
            Amendment.builder().year(2002).number("86th").title("Eighty-sixth Amendment").description("Made free and compulsory education a fundamental right for children aged 6-14.").build(),
            Amendment.builder().year(2005).number("93rd").title("Ninety-third Amendment").description("Enabled reservation in private unaided educational institutions.").build(),
            Amendment.builder().year(2019).number("103rd").title("Hundred and third Amendment").description("Provided 10% reservation for Economically Weaker Sections (EWS).").build()
        );
        amendmentRepository.saveAll(amendments);
    }

    private void seedQuizzes() throws Exception {
        // Quiz 1: Fundamental Rights
        Quiz quiz1 = Quiz.builder().title("Fundamental Rights Quiz")
                .description("Test your knowledge about the Fundamental Rights enshrined in the Indian Constitution.")
                .difficulty("Easy").build();
        quiz1 = quizRepository.save(quiz1);

        List<Question> q1Questions = List.of(
            Question.builder().quiz(quiz1).questionText("Which article of the Indian Constitution guarantees the Right to Equality?")
                .options(objectMapper.writeValueAsString(List.of("Article 12", "Article 14", "Article 19", "Article 21")))
                .correctIndex(1).explanation("Article 14 guarantees equality before law.").build(),
            Question.builder().quiz(quiz1).questionText("How many Fundamental Rights are mentioned in the Indian Constitution?")
                .options(objectMapper.writeValueAsString(List.of("5", "6", "7", "8")))
                .correctIndex(1).explanation("There are 6 Fundamental Rights.").build(),
            Question.builder().quiz(quiz1).questionText("Which article is called the 'Heart and Soul' of the Constitution?")
                .options(objectMapper.writeValueAsString(List.of("Article 14", "Article 19", "Article 21", "Article 32")))
                .correctIndex(3).explanation("Dr. Ambedkar called Article 32 the 'heart and soul' of the Constitution.").build(),
            Question.builder().quiz(quiz1).questionText("Freedom of Speech and Expression is guaranteed under which article?")
                .options(objectMapper.writeValueAsString(List.of("Article 14", "Article 19(1)(a)", "Article 21", "Article 25")))
                .correctIndex(1).explanation("Article 19(1)(a) guarantees freedom of speech and expression.").build(),
            Question.builder().quiz(quiz1).questionText("Right to Education was made a Fundamental Right by which amendment?")
                .options(objectMapper.writeValueAsString(List.of("42nd Amendment", "73rd Amendment", "86th Amendment", "93rd Amendment")))
                .correctIndex(2).explanation("The 86th Amendment Act, 2002 inserted Article 21A.").build()
        );
        questionRepository.saveAll(q1Questions);

        // Quiz 2: Constitutional History
        Quiz quiz2 = Quiz.builder().title("Constitutional History")
                .description("How well do you know the history behind the Indian Constitution?")
                .difficulty("Medium").build();
        quiz2 = quizRepository.save(quiz2);

        List<Question> q2Questions = List.of(
            Question.builder().quiz(quiz2).questionText("Who is known as the 'Father of the Indian Constitution'?")
                .options(objectMapper.writeValueAsString(List.of("Jawaharlal Nehru", "Mahatma Gandhi", "B.R. Ambedkar", "Rajendra Prasad")))
                .correctIndex(2).explanation("Dr. B.R. Ambedkar is known as the Father of the Indian Constitution.").build(),
            Question.builder().quiz(quiz2).questionText("When was the Indian Constitution adopted?")
                .options(objectMapper.writeValueAsString(List.of("January 26, 1950", "August 15, 1947", "November 26, 1949", "January 26, 1949")))
                .correctIndex(2).explanation("The Constitution was adopted on November 26, 1949.").build(),
            Question.builder().quiz(quiz2).questionText("How long did it take to draft the Indian Constitution?")
                .options(objectMapper.writeValueAsString(List.of("1 year", "2 years, 11 months, 18 days", "3 years", "5 years")))
                .correctIndex(1).explanation("It took 2 years, 11 months and 18 days.").build(),
            Question.builder().quiz(quiz2).questionText("Who was the President of the Constituent Assembly?")
                .options(objectMapper.writeValueAsString(List.of("B.R. Ambedkar", "Rajendra Prasad", "Jawaharlal Nehru", "Sardar Patel")))
                .correctIndex(1).explanation("Dr. Rajendra Prasad was the President.").build(),
            Question.builder().quiz(quiz2).questionText("Which amendment is known as the 'Mini Constitution'?")
                .options(objectMapper.writeValueAsString(List.of("1st Amendment", "42nd Amendment", "44th Amendment", "73rd Amendment")))
                .correctIndex(1).explanation("The 42nd Amendment is called the 'Mini Constitution'.").build()
        );
        questionRepository.saveAll(q2Questions);

        // Quiz 3: Fundamental Duties
        Quiz quiz3 = Quiz.builder().title("Fundamental Duties")
                .description("Test your understanding of the duties every citizen must follow.")
                .difficulty("Easy").build();
        quiz3 = quizRepository.save(quiz3);

        List<Question> q3Questions = List.of(
            Question.builder().quiz(quiz3).questionText("In which part of the Constitution are Fundamental Duties listed?")
                .options(objectMapper.writeValueAsString(List.of("Part III", "Part IV", "Part IV-A", "Part V")))
                .correctIndex(2).explanation("Fundamental Duties are in Part IV-A under Article 51A.").build(),
            Question.builder().quiz(quiz3).questionText("How many Fundamental Duties are there currently?")
                .options(objectMapper.writeValueAsString(List.of("10", "11", "12", "9")))
                .correctIndex(1).explanation("There are 11 Fundamental Duties.").build(),
            Question.builder().quiz(quiz3).questionText("Which amendment added Fundamental Duties to the Constitution?")
                .options(objectMapper.writeValueAsString(List.of("1st Amendment", "42nd Amendment", "44th Amendment", "86th Amendment")))
                .correctIndex(1).explanation("The 42nd Amendment (1976) added Fundamental Duties.").build(),
            Question.builder().quiz(quiz3).questionText("From which country was the concept of Fundamental Duties borrowed?")
                .options(objectMapper.writeValueAsString(List.of("USA", "UK", "Former Soviet Union", "France")))
                .correctIndex(2).explanation("Borrowed from the former Soviet Union (USSR).").build(),
            Question.builder().quiz(quiz3).questionText("Which of the following is NOT a Fundamental Duty?")
                .options(objectMapper.writeValueAsString(List.of("Respect the National Flag", "Pay taxes regularly", "Protect natural environment", "Develop scientific temper")))
                .correctIndex(1).explanation("Paying taxes is a legal obligation, not a Fundamental Duty.").build()
        );
        questionRepository.saveAll(q3Questions);
    }

    private void seedRights() throws Exception {
        List<Right> rights = List.of(
            Right.builder().title("Denied Entry to a Restaurant")
                .description("You are denied entry to a restaurant because of your caste. What are your constitutional rights?")
                .situation("Ravi, a citizen, visits a popular restaurant but is told he cannot enter because of his caste.")
                .rightsJson(objectMapper.writeValueAsString(List.of(
                    "Article 15 - Prohibition of discrimination on grounds of religion, race, caste, sex, or place of birth",
                    "Article 17 - Abolition of Untouchability")))
                .guidance("Under Article 15, no citizen can be discriminated against in access to shops, restaurants, and public places. Article 17 abolishes untouchability.")
                .outcome("Ravi can file a police complaint and approach the court.").build(),
            Right.builder().title("Wrongful Arrest")
                .description("You are arrested without being told the reason. Know your rights during an arrest.")
                .situation("Priya is arrested by police officers. They do not tell her why she is being arrested.")
                .rightsJson(objectMapper.writeValueAsString(List.of(
                    "Article 22 - Protection against arrest and detention",
                    "Article 21 - Right to Life and Personal Liberty")))
                .guidance("Under Article 22, every arrested person must be informed of the grounds of arrest and must be presented before a magistrate within 24 hours.")
                .outcome("Priya or her family can file a habeas corpus petition.").build(),
            Right.builder().title("Child Labour in a Factory")
                .description("You notice children working in a factory. What does the Constitution say about this?")
                .situation("Amit notices several children under 14 working in a hazardous factory.")
                .rightsJson(objectMapper.writeValueAsString(List.of(
                    "Article 24 - Prohibition of employment of children in factories",
                    "Article 21A - Right to Education",
                    "Article 39(e) - Tender age of children not abused")))
                .guidance("Article 24 directly prohibits employment of children below 14 in factories.")
                .outcome("The factory owner can face prosecution under the Child Labour Act.").build(),
            Right.builder().title("Freedom of Speech Online")
                .description("Your social media post criticizing a government policy is taken down.")
                .situation("Kavya posts a criticism of a government policy on social media. Her post is taken down.")
                .rightsJson(objectMapper.writeValueAsString(List.of(
                    "Article 19(1)(a) - Freedom of Speech and Expression",
                    "Article 19(2) - Reasonable Restrictions")))
                .guidance("Article 19(1)(a) guarantees freedom of speech. Peaceful, fact-based criticism is protected speech.")
                .outcome("Kavya can challenge the takedown in court.").build()
        );
        rightRepository.saveAll(rights);
    }

    private void seedFlashcards() {
        List<Flashcard> cards = List.of(
            Flashcard.builder().front("What is the Preamble?").back("The Preamble is the introduction to the Constitution that declares India as a Sovereign, Socialist, Secular, Democratic Republic.").build(),
            Flashcard.builder().front("Article 14").back("Right to Equality — The State shall not deny to any person equality before the law.").build(),
            Flashcard.builder().front("Article 19").back("Protection of 6 freedoms — speech & expression, peaceful assembly, forming associations, free movement, residence, and practicing any profession.").build(),
            Flashcard.builder().front("Article 21").back("Right to Life and Personal Liberty — No person shall be deprived of life or personal liberty except according to procedure established by law.").build(),
            Flashcard.builder().front("Article 32").back("Right to Constitutional Remedies — Called the 'Heart and Soul' of the Constitution.").build(),
            Flashcard.builder().front("Article 51A").back("Fundamental Duties — Lists 11 duties of every citizen.").build(),
            Flashcard.builder().front("Directive Principles").back("Part IV (Articles 36-51) — Guidelines for the government. Not enforceable by courts.").build(),
            Flashcard.builder().front("42nd Amendment").back("Known as 'Mini Constitution' (1976) — Added Fundamental Duties, words 'Socialist' and 'Secular' to Preamble.").build(),
            Flashcard.builder().front("Dr. B.R. Ambedkar").back("Chairman of the Drafting Committee, known as the 'Father of the Indian Constitution.'").build(),
            Flashcard.builder().front("Emergency Provisions").back("Articles 352-360 allow the President to declare emergency which can temporarily alter the federal structure.").build()
        );
        flashcardRepository.saveAll(cards);
    }

    private void seedUsers() {
        List<User> users = List.of(
            User.builder().name("Admin User").email("admin@constitutionconnect.in").password(passwordEncoder.encode("admin123")).role(Role.ROLE_ADMIN).streak(12).points(2450).joinedDate(LocalDate.of(2024, 1, 15)).build(),
            User.builder().name("Prof. Sharma").email("educator@constitutionconnect.in").password(passwordEncoder.encode("educator123")).role(Role.ROLE_EDUCATOR).streak(30).points(5200).joinedDate(LocalDate.of(2024, 2, 1)).build(),
            User.builder().name("Ravi Kumar").email("citizen@constitutionconnect.in").password(passwordEncoder.encode("citizen123")).role(Role.ROLE_CITIZEN).streak(7).points(890).joinedDate(LocalDate.of(2024, 6, 10)).build(),
            User.builder().name("Adv. Priya Nair").email("legal@constitutionconnect.in").password(passwordEncoder.encode("legal123")).role(Role.ROLE_LEGAL).streak(15).points(3100).joinedDate(LocalDate.of(2024, 3, 20)).build(),
            User.builder().name("Anita Desai").email("anita@example.com").password(passwordEncoder.encode("password123")).role(Role.ROLE_CITIZEN).streak(3).points(320).joinedDate(LocalDate.of(2024, 8, 5)).build(),
            User.builder().name("Vikram Singh").email("vikram@example.com").password(passwordEncoder.encode("password123")).role(Role.ROLE_CITIZEN).streak(21).points(1750).joinedDate(LocalDate.of(2024, 4, 12)).build(),
            User.builder().name("Dr. Meena Iyer").email("meena@example.com").password(passwordEncoder.encode("password123")).role(Role.ROLE_EDUCATOR).streak(45).points(6800).joinedDate(LocalDate.of(2024, 1, 20)).build(),
            User.builder().name("Adv. Rajesh Gupta").email("rajesh@example.com").password(passwordEncoder.encode("password123")).role(Role.ROLE_LEGAL).streak(10).points(2100).joinedDate(LocalDate.of(2024, 5, 18)).build()
        );
        userRepository.saveAll(users);
    }
}
