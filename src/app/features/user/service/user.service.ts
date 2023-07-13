/*
 * Copyright(c) 2023 Luvina Software Company
 *
 * User.component.ts , 6/30/2023 11:13 AM, LA31-AM-ThoPV
 */

import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AppConstants } from 'src/app/app-constants';
import { ListUser } from '../model/list-user';
import { Condition } from '../model/condition';
import { UserUtils } from '../utils/user.utils';
import { Error } from 'src/app/shared/model/error';
import { UserAddEdit } from '../model/user-add-edit';

@Injectable({
  providedIn: 'root'
})
/**
 * Represents a service for managing users.
 * 
 * @author [ThoPV]
 */
export class UserService {

  listCondition: Condition = new Condition();
  dataUserConfirm : UserAddEdit = new UserAddEdit();

  constructor(private httpClient: HttpClient) { }

  /**
  * Retrieves a list of users based on the provided employee name and department ID.
  * 
  * @param employeeName - The name of the employee to search for.
  * @param departmentId - The ID of the department to filter users by.
  * @returns An Observable of type any.
  */
  getListUsersCondition(listCondition: Condition): Observable<any> {
    listCondition.employeeName = UserUtils.replacePercent(listCondition.employeeName)

    return this.httpClient.get(AppConstants.BASE_URL_API + '/employee' + '?employee_name=' + listCondition.employeeName +
      '&department_id=' + listCondition.departmentId + '&ord_employee_name=' + listCondition.ordEmployeeName + '&ord_certification_name=' + listCondition.ordCertificationName +
      '&ord_end_date=' + listCondition.ordEndDate + '&offset=' + listCondition.offset + '&limit=' + listCondition.limit)
  }

  getDataUserConfirm() {
    return this.dataUserConfirm;
  }

  setDataUserConfirm(dataUserConfirm : any){
    this.dataUserConfirm = dataUserConfirm
  }

}
