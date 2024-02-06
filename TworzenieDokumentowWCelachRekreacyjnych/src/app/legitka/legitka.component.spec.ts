import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LegitkaComponent } from './legitka.component';

describe('LegitkaComponent', () => {
  let component: LegitkaComponent;
  let fixture: ComponentFixture<LegitkaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [LegitkaComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(LegitkaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
