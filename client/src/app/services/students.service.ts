import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Payment, Student} from "../model/students.model";
import {environment} from "../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class StudentsService {

  constructor(private http: HttpClient) {
  }

  public getAllPayments(): Observable<Array<Payment>> {
    return this.http.get<Array<Payment>>(`http://localhost:8083/payments`);
  }
  public getAllStudents(): Observable<Array<Student>> {
    return this.http.get<Array<Student>>(`http://localhost:8083/students`);
  }
  public getStudentPayments(code:string): Observable<Array<Payment>> {
    return this.http.get<Array<Payment>>(`http://localhost:8083/students/${code}/payments`);
  }
}
