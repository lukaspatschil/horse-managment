import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders, HttpParams } from "@angular/common/http";
import { Globals } from "../global/globals";
import { Observable } from "rxjs";
import { Horse } from "../dto/horse";
import { SearchHorse } from '../dto/searchHorse';

const httpOptions = {
  headers: new HttpHeaders({
    "Content-Type": "application/json"
  })
};

@Injectable({
  providedIn: "root"
})
export class HorseService {
  private messageBaseUri: string = this.globals.backendUri + "/horses";

  constructor(private httpClient: HttpClient, private globals: Globals) {}

  getHorseById(id:number): Observable<Horse> {
    return this.httpClient.get<Horse>(this.messageBaseUri + "/" + id);
  }

  /**
   * Loads all the current horses form the backend
   */
  getHorse(): Observable<Horse[]> {
    return this.httpClient.get<Horse[]>(this.messageBaseUri);
  }

  getHorsesfromOwner(id: Number): Observable<Horse[]> {
    return this.httpClient.get<Horse[]>(this.messageBaseUri + "/owners/" + id);
  }

  addHorse(horse: Horse): Observable<Horse> {
    return this.httpClient.post<Horse>(this.messageBaseUri, horse, httpOptions);
  }

  deleteHorse(horse: Horse): Observable<Horse> {
    return this.httpClient.delete<Horse>(this.messageBaseUri + "/" + horse.id);
  }

  updateHorse(horse: Horse): Observable<Horse> {
    return this.httpClient.put<Horse>(
      this.messageBaseUri + "/" + horse.id,
      horse,
      httpOptions
    );
  }

  searchHorse(horse:SearchHorse):Observable<Horse[]> {
    let params = new HttpParams();

    console.log(horse);
    
    if (horse.name) {
      params = params.set("name", horse.name);
    }
    if (horse.notes) {
      params = params.set("notes", horse.notes);
    }
    if (horse.birthday) {
      params = params.set("birthday", horse.birthday);
    }
    if (horse.rating) {
      params = params.set("rating", horse.rating.toString());
    }
    if (horse.race) {
      params = params.set("race", horse.race);
    }

    return this.httpClient.get<Horse[]>(this.messageBaseUri + '/search', {params: params});
  }
}
