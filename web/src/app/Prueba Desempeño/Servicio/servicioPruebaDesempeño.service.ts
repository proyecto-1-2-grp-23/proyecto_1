import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root',
})
export class ServicioPruebaDesempeñoService {
  private backUrl: string = environment.baseUrlPruebas + '/pruebas';

  constructor(private http: HttpClient) {}

  crearPruebaDesempeno(prueba: any): Observable<any> {
    return this.http.post<any>(`${this.backUrl}/evaluacion_desempeño`, prueba);
  }
}
