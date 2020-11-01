package com.ht.dao;

import com.ht.model.EnterpriseProject;
import java.util.List;

public interface IEnterpriseProjectDao {

    int insertEnterpriseProject(EnterpriseProject enterpriseProject);

    String updateEnterpriseProject(EnterpriseProject enterpriseProject);

    List<EnterpriseProject> getEnterpriseProjectByProjectName(String projectName);

    String deleteEnterpriseProject(String projectId);

    EnterpriseProject getEnterpriseProjectByProjectId(String projectId);

}
