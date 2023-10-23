/* tslint:disable:no-unused-variable */

import { TestBed, async, inject } from '@angular/core/testing';
import { ServicioEmpresaService } from './servicio-empresa.service';

describe('Service: ServicioEmpresa', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [ServicioEmpresaService]
    });
  });

  it('should ...', inject([ServicioEmpresaService], (service: ServicioEmpresaService) => {
    expect(service).toBeTruthy();
  }));
});
