import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export class Config {
  id: number;
  name: string;
  value: number;
}

@Injectable({
  providedIn: 'root'
})
export class ConfigService {

  private baseUrl = 'http://localhost:8080/api/v1/config';

  constructor(private http: HttpClient) { }

  getConfig(id: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/${id}`);
  }

  updateConfig(id: number, value: any): Observable<Object> {
    return this.http.put(`${this.baseUrl}/${id}`, value);
  }

  getConfigList(): Observable<any> {
    return this.http.get(`${this.baseUrl}`);
  }

}
