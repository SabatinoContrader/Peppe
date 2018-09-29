import { Events } from 'ionic-angular';
import { User } from './../../models/User';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from '../../../node_modules/rxjs/Observable';
import 'rxjs/add/operator/map';
import { tap } from '../../../node_modules/rxjs/operators';
import { SERVER_API_URL } from '../../constants/constants';


/*
  Generated class for the UserProvider provider.

  See https://angular.io/guide/dependency-injection for more info on providers
  and Angular DI.
*/
@Injectable()
export class UserProvider {
  feedback: string;

  constructor(public http: HttpClient,public events: Events) {
    console.log('Hello UserProvider Provider');
  }

  login(username: string, password: string): Observable<any> {
    return this.http.get<User>(SERVER_API_URL + '/api/login?username='+username+'&password='+password).pipe(
      tap(response => {
        if (response != null) {
        sessionStorage.setItem("user", JSON.stringify(response));
        this.events.publish('user:created', response);  
        }
      })
    );
  }

  signup(user: User): Observable <boolean> {
    return this.http.post<boolean>(SERVER_API_URL + '/api/signupUser', user).pipe();
  }

  changeFeedback(message: string){
    this.feedback = message;
  }

  deleteFeedback(){
    this.feedback = "";
  }
}
