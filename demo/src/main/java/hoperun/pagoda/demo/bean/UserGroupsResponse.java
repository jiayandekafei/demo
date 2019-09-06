package hoperun.pagoda.demo.bean;

import java.util.List;

import hoperun.pagoda.demo.entity.UserGroupTree;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserGroupsResponse {
    List<UserGroupTree> groups;
}
