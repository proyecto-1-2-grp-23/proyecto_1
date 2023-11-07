import { TestBed } from '@angular/core/testing';

import { ServicioProyectosService } from './servicio-proyectos.service';

describe('ServicioProyectosService', () => {
  let service: ServicioProyectosService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ServicioProyectosService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
