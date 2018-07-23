import { User } from './../../models/User';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from '../../../node_modules/rxjs/Observable';
import 'rxjs/add/operator/map';

/*
  Generated class for the UserProvider provider.

  See https://angular.io/guide/dependency-injection for more info on providers
  and Angular DI.
*/
@Injectable()
export class UserProvider {
  feedback: string;

  constructor(public http: HttpClient) {
    console.log('Hello UserProvider Provider');
  }

  login(username: string, password: string): Observable<any> {
    return this.http.get<User>('http://localhost:58708/api/login?username='+username+'&password='+password).pipe();
  }

  signup(user: User): Observable <boolean> {
    return this.http.post<boolean>('http://localhost:58708/api/signupUser', user)
    .pipe();
  }

  changeFeedback(message: string){
    this.feedback = message;
  }

  deleteFeedback(){
    this.feedback = "";
  }
}
