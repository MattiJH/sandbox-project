import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { ItemService } from './item.service';

describe('ItemService', () => {
  let service: ItemService;
  let httpMock: HttpTestingController;
  const baseUrl = 'http://localhost:8080';

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [ItemService]
    });

    service = TestBed.inject(ItemService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify();
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should get all items', () => {
    const mockItems = [
      { id: 1, name: 'Item 1', description: 'Description 1' },
      { id: 2, name: 'Item 2', description: 'Description 2' }
    ];

    service.getItems().subscribe(items => {
      expect(items).toEqual(mockItems);
    });

    const req = httpMock.expectOne(`${baseUrl}/api/items`);
    expect(req.request.method).toBe('GET');
    req.flush(mockItems);
  });

  it('should get item by id', () => {
    const mockItem = { id: 1, name: 'Item 1', description: 'Description 1' };
    const itemId = 1;

    service.getItem(itemId).subscribe(item => {
      expect(item).toEqual(mockItem);
    });

    const req = httpMock.expectOne(`${baseUrl}/api/items/${itemId}`);
    expect(req.request.method).toBe('GET');
    req.flush(mockItem);
  });

  it('should create new item', () => {
    const mockItem = { name: 'New Item', description: 'New Description' };
    const mockResponse = { id: 1, name: 'New Item', description: 'New Description' };

    service.createItem(mockItem).subscribe(response => {
      expect(response).toEqual(mockResponse);
    });

    const req = httpMock.expectOne(`${baseUrl}/api/items`);
    expect(req.request.method).toBe('POST');
    expect(req.request.body).toEqual(mockItem);
    req.flush(mockResponse);
  });

  it('should update item', () => {
    const mockItem = { id: 1, name: 'Updated Item', description: 'Updated Description' };

    service.updateItem(mockItem).subscribe(response => {
      expect(response).toEqual(mockItem);
    });

    const req = httpMock.expectOne(`${baseUrl}/api/items/${mockItem.id}`);
    expect(req.request.method).toBe('PUT');
    expect(req.request.body).toEqual(mockItem);
    req.flush(mockItem);
  });

  it('should delete item', () => {
    const itemId = 1;

    service.deleteItem(itemId).subscribe(response => {
      expect(response).toBeNull();
    });

    const req = httpMock.expectOne(`${baseUrl}/api/items/${itemId}`);
    expect(req.request.method).toBe('DELETE');
    req.flush(null);
  });
}); 