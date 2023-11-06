import { TestBed } from '@angular/core/testing';

import { ServicioEntrevistasService } from './servicio-entrevistas.service';

describe('ServicioEntrevistasService', () => {
  let service: ServicioEntrevistasService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ServicioEntrevistasService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
