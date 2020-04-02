import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders, HttpParams } from "@angular/common/http";
import { Globals } from "../global/globals";
import { Observable } from "rxjs";
import { Horse } from "../dto/horse";

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

  searchHorse(horse:Horse):Observable<Horse[]> {
    let headers = new HttpHeaders();
    headers.append('Content-Type', 'application/json');

    let params = new HttpParams().set("name", horse.name).set("notes", horse.notes).set("birthday", horse.birthday).set("race", horse.race).set("rating", horse.rating.toString());

    return this.httpClient.get<Horse[]>(this.messageBaseUri + '/search', {headers: headers, params: params});
  }
}
