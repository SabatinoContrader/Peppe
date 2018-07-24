import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from '../../../node_modules/rxjs/Observable';
import { Stop } from '../../models/Stop';

/*
  Generated class for the StopProvider provider.

  See https://angular.io/guide/dependency-injection for more info on providers
  and Angular DI.
*/
@Injectable()
export class StopProvider {

  constructor(public http: HttpClient) {
    console.log('Hello StopProvider Provider');
  }

        //user corrente da backend?
myStopsList(): Observable<any> {
  return this.http.get<any>('http://localhost:58708/api/myStopsList')
  .pipe();
}


prolungaSosta(minute: number, stop: Stop): any {
// var newFinish= new Date((new Date(stop.finish)).getTime() + (60*1000*minute));
// console.log(newFinish.toString());
// var newStop: Stop = new Stop(stop.id_stop, stop.address, stop.start, newFinish, stop.name, stop.price);
return this.http.put<any>('http://localhost:58708/api/extensionstops/'+stop.id_stop, JSON.parse(minute.toString()))
.pipe();
}

}
