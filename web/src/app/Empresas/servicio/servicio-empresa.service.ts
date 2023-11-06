import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class ServicioCandidatosService {
  private backUrl: string = environment.baseUrl + '/users';

  constructor(private http: HttpClient) {}

  empresaRegistro(empresa: any): Observable<any> {
    return this.http.post<any>(`${this.backUrl}/empresa`, empresa);
  }
}
