/* tslint:disable:no-unused-variable */

import { TestBed, async, inject } from '@angular/core/testing';
import { ServicioUsuariosService } from './servicio-usuarios.service';

describe('Service: ServicioUsuarios', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [ServicioUsuariosService]
    });
  });

  it('should ...', inject([ServicioUsuariosService], (service: ServicioUsuariosService) => {
    expect(service).toBeTruthy();
  }));
});
