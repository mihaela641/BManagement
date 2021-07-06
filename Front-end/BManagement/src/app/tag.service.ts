import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Tag } from './Entities/Tag';

@Injectable({
  providedIn: 'root'
})
export class TagService {
  private URL = environment.apiBaseURL;
  
  constructor(private http: HttpClient) { }

  getTags(params:HttpParams):Observable<any>{
    return this.http.get<any>(this.URL+"/tags/paging",{params});
  }

  getTag(id:number):Observable<Tag>{
    return this.http.get<Tag>(this.URL+"/tags/"+id);
  }

  addTag(tag:Tag):Observable<Tag>{
    return this.http.post<Tag>(this.URL+"/tags",tag);
  }

  updateTag(tag:Tag,id:number):Observable<Tag>{
    return this.http.put<Tag>(this.URL+"/tags/"+id,tag);
  }

  deleteTag(id:number):Observable<any>{
    return this.http.delete(this.URL+"/tags/"+id);
  }
}
