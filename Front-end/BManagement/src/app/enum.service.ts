import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class EnumService {
  private URL = environment.apiBaseURL;
  constructor(private http: HttpClient ) { }

  getCountries():Observable<any>{
    return this.http.get<any>(this.URL+"/enums/countries");
  }
}
