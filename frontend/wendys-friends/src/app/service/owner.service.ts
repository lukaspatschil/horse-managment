import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {Globals} from '../global/globals';
import {Observable} from 'rxjs';
import {Owner} from '../dto/owner';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json'
  })
}

@Injectable({
  providedIn: 'root'
})
export class OwnerService {

  private messageBaseUri: string = this.globals.backendUri + '/owners';

  constructor(private httpClient: HttpClient, private globals: Globals) {
  }

  /**
   * Loads specific owner from the backend
   * @param id of owner to load
   */
  getOwnerById(id: number): Observable<Owner> {
    console.log('Load owner details for ' + id);
    return this.httpClient.get<Owner>(this.messageBaseUri + '/' + id);
  }

  /**
   * Loads all the current user form the backend
   */
  getOwner():Observable<Owner[]> {
    return this.httpClient.get<Owner[]>(this.messageBaseUri);
  }

  /**
   * 
   * @param owner 
   */
  addOwner(owner: Owner): Observable<Owner> {
    return this.httpClient.post<Owner>(this.messageBaseUri, owner, httpOptions);
  }

  /**
   * 
   * @param owner 
   */
  deleteOwner(owner:Owner):Observable<Owner> {
    return this.httpClient.delete<Owner>(this.messageBaseUri + '/' + owner.id);
  }

  /**
   * 
   * @param owner 
   */
  updateOwner(owner:Owner):Observable<Owner> {
    return this.httpClient.put<Owner>(this.messageBaseUri + '/' + owner.id, owner, httpOptions);
  }

  searchOwner(owner:Owner):Observable<Owner[]> {
    let params = new HttpParams().set("params", owner.name);

    return this.httpClient.get<Owner[]>(this.messageBaseUri + '/search', {params: params});
  }
}
