import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { DepartmentService } from '../../department/service/department.service';
import { UserService } from '../service/user.service';
import { UserAddEdit } from '../model/user-add-edit';

@Component({
  selector: 'app-confirm',
  templateUrl: './confirm.component.html',
  styleUrls: ['./confirm.component.css']
})
export class ConfirmComponent {

  nameDepartment: string | undefined = ""

  dataUserConfirm: UserAddEdit = new UserAddEdit();
  constructor(private router: ActivatedRoute, private route: Router,
    private departmentService: DepartmentService, private userService: UserService) { }

  ngOnInit() {
    this.dataUserConfirm = this.userService.getDataUserConfirm();

    this.getDepartmentNameById(this.dataUserConfirm.departmentId);
  }

  getDepartmentNameById(id: number) {
    this.departmentService.getDepartmentById(id).subscribe((data) => {
      this.nameDepartment = data.departmentName
    })
  }

  onBack() {
    this.userService.setDataUserConfirm(this.dataUserConfirm)
    this.route.navigate(['user', 'add-edit'])
  }
}
