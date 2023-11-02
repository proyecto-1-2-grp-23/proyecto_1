import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProgramarEntrevistaComponent } from './programar-entrevista.component';

describe('ProgramarEntrevistaComponent', () => {
  let component: ProgramarEntrevistaComponent;
  let fixture: ComponentFixture<ProgramarEntrevistaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProgramarEntrevistaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ProgramarEntrevistaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
