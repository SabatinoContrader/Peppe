import { Injectable } from '@angular/core';
import { Payment } from '../models/Payment';
import { Observable, of } from 'rxjs';
import {HttpClient} from '@angular/common/http';
import { User } from '../models/User';
import { tap, catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class PaymentService {

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.error(error);
      console.log('${operation} failed: ${error.message}');
      return of(result as T);
    };
  }

constructor(private http: HttpClient) { }

getpayments(): Observable <any> {
  var user: User = JSON.parse(sessionStorage.getItem("user"));
  return this.http.get<any>('http://localhost:58708/api/paymentList?username='+user.username)
  .pipe(tap((response) => console.log("Utente"), catchError(this.handleError("login error", {})))
);
}

}
