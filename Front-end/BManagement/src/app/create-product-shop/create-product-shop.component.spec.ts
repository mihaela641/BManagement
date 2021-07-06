import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateProductShopComponent } from './create-product-shop.component';

describe('CreateProductShopComponent', () => {
  let component: CreateProductShopComponent;
  let fixture: ComponentFixture<CreateProductShopComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CreateProductShopComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateProductShopComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
