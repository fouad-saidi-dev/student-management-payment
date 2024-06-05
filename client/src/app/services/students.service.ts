import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Payment, Student} from "../model/students.model";
import {environment} from "../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class StudentsService {

  baseUrl: string = 'http://localhost:8083'

  constructor(private http: HttpClient) {
  }

  public getAllPayments(): Observable<Array<Payment>> {
    return this.http.get<Array<Payment>>(`${this.baseUrl}/payments`);
  }

  public getAllStudents(): Observable<Array<Student>> {
    return this.http.get<Array<Student>>(`${this.baseUrl}/students`);
  }

  public getStudentPayments(code: string): Observable<Array<Payment>> {
    return this.http.get<Array<Payment>>(`${this.baseUrl}/students/${code}/payments`);
  }

  public savePayment(formData: any): Observable<Payment> {
    return this.http.post<Payment>(`${this.baseUrl}/payments`, formData);
  }

  getPaymentDetails(paymentId: number) {
    return this.http.get(`${this.baseUrl}/paymentFile/${paymentId}`,
      {responseType: 'blob'})
  }
}
