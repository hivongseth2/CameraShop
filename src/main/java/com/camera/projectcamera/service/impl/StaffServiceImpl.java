    package com.camera.projectcamera.service.impl;

    import com.camera.projectcamera.entity.Accounts;
    import com.camera.projectcamera.entity.Categories;
    import com.camera.projectcamera.entity.Role;
    import com.camera.projectcamera.entity.Staff;
    import com.camera.projectcamera.model.request.StaffRequest;
    import com.camera.projectcamera.repository.AccountRepository;
    import com.camera.projectcamera.repository.CustomerRepository;
    import com.camera.projectcamera.repository.StaffRepository;
    import com.camera.projectcamera.service.AccountService;
    import com.camera.projectcamera.service.RoleService;
    import com.camera.projectcamera.service.StaffService;
    import lombok.RequiredArgsConstructor;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.HttpStatus;
    import org.springframework.stereotype.Service;
    import org.springframework.web.server.ResponseStatusException;

    import java.util.List;

    @Service
    @RequiredArgsConstructor
    public class StaffServiceImpl implements StaffService {

        @Autowired
        private StaffRepository staffRepository;

        @Override
        public List<Staff> getStaff() {
            return staffRepository.findAll();
        }

        @Override
        public Staff getStaffById(Long staffId) {
            Staff staff = staffRepository
                    .findById(staffId)
                    .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Mặt hàng không được tìm thấy với id" + staffId));
            return staff;
        }

        private final CustomerRepository customerRepo;
        private final AccountRepository accountRepository;
        private final AccountService accountService;
        private final RoleService roleService;

        @Override
        public Staff addStaff(StaffRequest staffRequest) {
            Accounts account = new Accounts();
            try{
                try{
                    Role role = roleService.getRoleById(staffRequest.getRoleId());

                    account.setUserName(staffRequest.getUserName());
                    account.setPassword(staffRequest.getPassWord());
                    account.setRole(role);
                    accountRepository.save(account);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                    System.out.println("Error when save account");
                }
                finally {
                    Staff staff = new Staff();
                    staff.setFirstName(staffRequest.getFirstName());
                    staff.setLastName(staffRequest.getLastName());
                    staff.setPhone(staffRequest.getPhone());
                    staff.setEmail(staffRequest.getEmail());
                    staff.setStreet(staffRequest.getStreet());
                    staff.setCity(staffRequest.getCity());
                    staff.setStatus(1);
                    staff.setAccount(account);
                    return staffRepository.save(staff);
                }
            }catch(Exception e)
            {
                e.printStackTrace();
                return null;
            }
        }
    }
