import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root',
})
export class ServicioEntrevistasService {
  private backUrl: string = environment.baseUrl + '/users';

  constructor(private http: HttpClient) {}

  crearEntrevistas(entrevista: any): Observable<any> {
    return this.http.post<any>(`${this.backUrl}/entrevistas`, entrevista);
  }

  listarEntrevistas(): Observable<any> {
    return this.http.get<any>(`${this.backUrl}/entrevistas`);
  }
}
