/* tslint:disable:no-unused-variable */

import { TestBed, async, inject } from '@angular/core/testing';
import { ServicioCandidatosService } from './servicio-candidatos.service';

describe('Service: ServicioCandidatos', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [ServicioCandidatosService]
    });
  });

  it('should ...', inject([ServicioCandidatosService], (service: ServicioCandidatosService) => {
    expect(service).toBeTruthy();
  }));
});
