package ds.testingsystem.data.repos;

import ds.testingsystem.data.model.AnswerVariant;
import ds.testingsystem.data.repos.template.Repository;

public class AnswerVariantRepository extends Repository<AnswerVariant, Long> {
    public AnswerVariantRepository(Class<AnswerVariant> clazz) {super(clazz);}
}
