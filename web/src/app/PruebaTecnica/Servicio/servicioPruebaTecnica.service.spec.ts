/* tslint:disable:no-unused-variable */

import { TestBed, async, inject } from '@angular/core/testing';
import { ServicioPruebaTecnicaService } from './servicioPruebaTecnica.service';

describe('Service: ServicioPruebaTecnica', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [ServicioPruebaTecnicaService]
    });
  });

  it('should ...', inject([ServicioPruebaTecnicaService], (service: ServicioPruebaTecnicaService) => {
    expect(service).toBeTruthy();
  }));
});
