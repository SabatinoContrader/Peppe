import { User } from './../../models/User';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from '../../../node_modules/rxjs';
import { Payment } from '../../models/Payment';
import { SERVER_API_URL } from '../../constants/constants';
import { AllPayment } from '../../models/AllPayment';

/*
  Generated class for the PaymentProvider provider.

  See https://angular.io/guide/dependency-injection for more info on providers
  and Angular DI.
*/
@Injectable()
export class PaymentProvider {

  constructor(private http: HttpClient) { }

getpayments(): Observable <AllPayment> {
  var user: User = JSON.parse(sessionStorage.getItem("user"));
  return this.http.get<AllPayment>(SERVER_API_URL + '/api/paymentList?username='+user.username)
  .pipe();
}

addPayment(quantity:number,id_slot:number,id_car:number,timeToAdd:number): Observable <number> {
  var user: User = JSON.parse(sessionStorage.getItem("user"));
  //payment and stop id are autoincrement, we pass 0, backend will fix
  var payment: Payment = new Payment(0,quantity,0,user.username,id_slot,id_car,timeToAdd);
  return this.http.post<number>(SERVER_API_URL + '/api/addPayment', payment)
  .pipe();
}
}
