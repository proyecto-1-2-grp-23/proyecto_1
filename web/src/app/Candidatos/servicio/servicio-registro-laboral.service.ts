import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ServicioRegistroLaboralService {
  private backUrl: string = environment.baseUrl + '/users';

  constructor(private http: HttpClient) { }

  candidatoRegistroLaboral(laboral: any): Observable<any> {
    return this.http.post<any>(`${this.backUrl}/dataLaboral`, laboral);
  }
}
