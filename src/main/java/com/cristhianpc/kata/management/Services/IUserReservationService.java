package com.cristhianpc.kata.management.Services;

import com.cristhianpc.kata.management.Dto.UserReservation.RequestUserReservation;
import com.cristhianpc.kata.management.Models.UserRevervation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface IUserReservationService {

    Page<UserRevervation> getAllByReservation(PageRequest pageRequest,Long id) throws Exception;
    Page<UserRevervation> getAllByReservator(PageRequest pageRequest,String email) throws Exception;

    UserRevervation getReservationById(Long id);

    UserRevervation createReservation(RequestUserReservation reservation) throws Exception;
    UserRevervation updateReservation(Long id, RequestUserReservation reservation) throws Exception;

    void deleteReservation(Long id) throws Exception;

}
