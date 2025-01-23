package org.example.servlet_study.service;

import org.example.servlet_study.dao.PracticeUserDao;
import org.example.servlet_study.dto.PracticeSignupDto;
import org.example.servlet_study.dto.ResponseDto;
import org.example.servlet_study.entity.PracticeUser;

public class PracticeUserService {
    private PracticeUserDao practiceUserDao;

    private static PracticeUserService practiceUserService;
    private PracticeUserService() {
        practiceUserDao = PracticeUserDao.getInstance();
    }

    public static PracticeUserService getInstance() {
        if (practiceUserService == null) {
            practiceUserService = new PracticeUserService();
        }
        return practiceUserService;
    }


    public ResponseDto<?> signup(PracticeSignupDto practiceSignupDto) {
        PracticeUser insertPracticeUser = practiceUserDao.signup(practiceSignupDto.toPracticeUser());
        if (insertPracticeUser != null) {
            return ResponseDto.fail("사용자 추가하지 못하였습니다");
        }
return ResponseDto.success(practiceSignupDto);
    }
}
