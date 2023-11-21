import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VerResultadoComponent } from './ver-resultado.component';

describe('VerResultadoComponent', () => {
  let component: VerResultadoComponent;
  let fixture: ComponentFixture<VerResultadoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ VerResultadoComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(VerResultadoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
