import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AppConstants } from 'src/app/app-constants';

@Injectable({
  providedIn: 'root'
})
export class CertificationService {

  constructor(private httpClient : HttpClient) { }

  /**
   * Retrieves a list of certifications.
   * 
   * @returns An Observable of type certifications.
   */
  getListCertification(): Observable<any> {
    return this.httpClient.get(AppConstants.BASE_URL_API + '/certification');
  }
}
