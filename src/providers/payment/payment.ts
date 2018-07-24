import { User } from './../../models/User';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from '../../../node_modules/rxjs';
import { Payment } from '../../models/Payment';

/*
  Generated class for the PaymentProvider provider.

  See https://angular.io/guide/dependency-injection for more info on providers
  and Angular DI.
*/
@Injectable()
export class PaymentProvider {

  constructor(private http: HttpClient) { }

getpayments(): Observable <any> {
  var user: User = JSON.parse(sessionStorage.getItem("user"));
  return this.http.get<any>('http://localhost:58708/api/paymentList?username='+user.username)
  .pipe();
}

addPayment(quantity:number,id_slot:number,id_car:number,timeToAdd:number): Observable <number> {
  var user: User = JSON.parse(sessionStorage.getItem("user"));
  //payment and stop id are autoincrement, we pass 0, backend will fix
  var payment: Payment = new Payment(0,quantity,0,user.username,id_slot,id_car,timeToAdd);
  return this.http.post<number>('http://localhost:58708/api/addPayment', payment)
  .pipe();
}
}
