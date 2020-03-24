import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Globals} from '../global/globals';
import {Observable} from 'rxjs';
import {Horse} from '../dto/horse';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json'
  })
}

@Injectable({
  providedIn: 'root'
})
export class HorseService {

  private messageBaseUri: string = this.globals.backendUri + '/horses';

  constructor(private httpClient: HttpClient, private globals: Globals) { }

  /**
   * Loads all the current horses form the backend
   */
  getHorse():Observable<Horse[]> {
    return this.httpClient.get<Horse[]>(this.messageBaseUri);
  }

  /**
   * 
   * @param horse 
   */
  addHorse(horse: Horse): Observable<Horse> {
    return this.httpClient.post<Horse>(this.messageBaseUri, horse, httpOptions);
  }

    /**
   * 
   * @param horse 
   */
  deleteHorse(horse:Horse):Observable<Horse> {
    return this.httpClient.delete<Horse>(this.messageBaseUri + '/' + horse.id);
  }

  updateHorse(horse:Horse):Observable<Horse> {
    return this.httpClient.put<Horse>(this.messageBaseUri + '/' + horse.id, horse, httpOptions);
  }
}
