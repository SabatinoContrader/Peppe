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

addPayment(quantity:number,id_slot:number,id_car:number,timeToAdd:number): Observable <number> {
  var user: User = JSON.parse(sessionStorage.getItem("user"));
  //payment and stop id are autoincrement, we pass 0, backend will fix
  var payment: Payment = new Payment(0,quantity,0,user.username,id_slot,id_car,timeToAdd);
  return this.http.post<number>('http://localhost:58708/api/addPayment', payment)
  .pipe(tap((response) => console.log("addPayment"), catchError(this.handleError("login error", {})))
);
}

}
