import { Component, ViewChild, ElementRef } from '@angular/core';
import { ListDepartment } from '../../department/model/list-department';
import { HttpClient } from '@angular/common/http';
import { UserService } from '../service/user.service';
import { DepartmentService } from '../../department/service/department.service';
import { ActivatedRoute, Router } from '@angular/router';
import { ListCertification } from '../../certification/model/list-certification';
import { CertificationService } from '../../certification/service/certification.service';
import { FormBuilder, Validators } from '@angular/forms';
import { BsDatepickerConfig } from 'ngx-bootstrap/datepicker';
import { UserAddEdit } from '../model/user-add-edit';

@Component({
  selector: 'app-add-edit',
  templateUrl: './add-edit.component.html',
  styleUrls: ['./add-edit.component.css']
})
export class AddEditComponent {

  @ViewChild('employeeAccount') employeeAccount! : ElementRef;

  bsConfig: Partial<BsDatepickerConfig> = {
    dateInputFormat: 'YYYY/MM/DD',
  };;

  infoFormData = this.formBuilder.group({
    employeeAccount: ['', Validators.required],
    departmentId: ['', Validators.required],
    employeeName: ['', Validators.required],
    employeeNameKata: ['', Validators.required],
    employeeBirthday: ['', Validators.required],
    employeeEmail: ['', Validators.required],
    employeeTelephone: ['', Validators.required],
    employeePassword: ['', Validators.required],
    employeePasswordAgain: ['', Validators.required],
    certificationName: ['', Validators.required],
    startDateCertification: ['', Validators.required],
    endDateCertification: ['', Validators.required],
    scoreCertification: ['', Validators.required]
  })

  dataUserConfirm: UserAddEdit = new UserAddEdit();

  constructor(
    public http: HttpClient,
    private userService: UserService,
    private departmentService: DepartmentService,
    private certificationService: CertificationService,
    private router: Router,
    private formBuilder: FormBuilder,
    private routeActivated: ActivatedRoute
  ) {
  }

  dataListDepartment: ListDepartment = new ListDepartment();
  dataListCertification: ListCertification = new ListCertification();

  ngOnInit() {
    this.getListCertification()
    this.getListDepartments()
    this.getDataConfirmBack()
  }

  ngAfterViewInit(){
    this.employeeAccount.nativeElement.focus();
  }

  getDataConfirmBack() {
    this.dataUserConfirm = this.userService.getDataUserConfirm()
    const checkFromConfirm = this.dataUserConfirm.employeeAccount.length > 0;

    console.log(checkFromConfirm == true ? "Từ màn hình confirm quay về" : "Không phải từ màn hình confirm");
    
  }

  /**
   * Retrieves the list of departments.
   * 
   */
  getListDepartments() {
    this.departmentService.getListDepartments().subscribe({
      next: (data) => {
        this.dataListDepartment = data;
      }
    })
  }


  getListCertification() {
    this.certificationService.getListCertification().subscribe({
      next: (data) => {
        this.dataListCertification = data
      }
    })
  }


  onSubmit() {
    if (this.infoFormData.value.employeeBirthday) {
      this.infoFormData.value.employeeBirthday = this.convertDateFormat(this.infoFormData.value.employeeBirthday?.toString() || '')
    }
    if (this.infoFormData.value.startDateCertification) {
      this.infoFormData.value.startDateCertification = this.convertDateFormat(this.infoFormData.value.startDateCertification?.toString() || '')
    }
    if (this.infoFormData.value.endDateCertification) {
      this.infoFormData.value.endDateCertification = this.convertDateFormat(this.infoFormData.value.endDateCertification?.toString() || '')
    }

    this.userService.setDataUserConfirm(this.infoFormData.value)
    this.router.navigate(['user', 'confirm'])
  }

  convertDateFormat(dateString: string): string {
    const date = new Date(dateString);
    const year = date.getFullYear();
    const month = this.formatNumber(date.getMonth() + 1);
    const day = this.formatNumber(date.getDate());

    return `${year}/${month}/${day}`;
  }

  formatNumber(number: number): string {
    return number < 10 ? `0${number}` : `${number}`;
  }
}
