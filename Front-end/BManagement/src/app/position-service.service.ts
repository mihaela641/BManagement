import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Position } from './Entities/Position';

@Injectable({
  providedIn: 'root'
})
export class PositionServiceService {
  private URL = environment.apiBaseURL;
  
  constructor(private http: HttpClient) { }
  getPositionsArray():Observable<any>{
    return this.http.get<any>(this.URL+"/positions");
  }

  getPositions(params:HttpParams):Observable<any>{
    return this.http.get<any>(this.URL+"/positions/paging",{params});
  }

  getPosition(id:number):Observable<Position>{
    return this.http.get<Position>(this.URL+"/positions/"+id);
  }

  addPosition(position:Position):Observable<Position>{
    return this.http.post<Position>(this.URL+"/positions",position);
  }

  updatePosition(position:Position,id:number):Observable<Position>{
    return this.http.put<Position>(this.URL+"/positions/"+id,position);
  }

  deletePosition(id:number):Observable<any>{
    return this.http.delete(this.URL+"/positions/"+id);
  }
}