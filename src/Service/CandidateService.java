package Service;

import Model.Skill;

import java.util.List;

public interface CandidateService {
    public boolean insertData(List<String> arr, String id);
    public boolean insertDataSkill(List<Skill> skills, String id);

    public String checkValidate(List<String> arr);
}
