/* tslint:disable:no-unused-variable */

import { TestBed, async, inject } from '@angular/core/testing';
import { ServicioPruebaDesempeñoService } from './servicioPruebaDesempeño.service';

describe('Service: ServicioPruebaDesempeño', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [ServicioPruebaDesempeñoService]
    });
  });

  it('should ...', inject([ServicioPruebaDesempeñoService], (service: ServicioPruebaDesempeñoService) => {
    expect(service).toBeTruthy();
  }));
});
