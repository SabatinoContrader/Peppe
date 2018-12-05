import { Injectable } from '@angular/core';
import { Payment } from '../_models/Payment';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { User } from '../_models/User';
import { tap, catchError } from 'rxjs/operators';
import { of } from 'rxjs/observable/of';
import { Stop } from '../_models/Stop';
import { Book } from '../_models/Book';

/**
 * @description
 * @class
 */
@Injectable()
export class BookService {

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
        console.error(error);
        console.log('${operation} failed: ${error.message}');
        return of(result as T);
    };
}


  constructor(private http: HttpClient) {
    
  }

  addBook(start:any, timeToAdd: number, id_slot: number, id_car: number, quantity): Observable<number> {
    var user: User = JSON.parse(sessionStorage.getItem("user"));
    var book: Book = new Book(0,user.username,id_slot,0, quantity,0,start,timeToAdd, id_car);
    return this.http.post<number>('http://localhost:58708/api/addBook', book)
        .pipe(tap((response) => console.log("addPayment"), catchError(this.handleError("login error", {})))
        );
}



}
