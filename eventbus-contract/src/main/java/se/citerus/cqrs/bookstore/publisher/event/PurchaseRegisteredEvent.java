package se.citerus.cqrs.bookstore.publisher.event;

import se.citerus.cqrs.bookstore.book.BookId;
import se.citerus.cqrs.bookstore.event.DomainEvent;
import se.citerus.cqrs.bookstore.publisher.PublisherId;

public class PurchaseRegisteredEvent extends DomainEvent<PublisherId> {

  public final BookId bookId;
  public final long price;

  public PurchaseRegisteredEvent(PublisherId publisherId, int version, long timestamp, BookId bookId, long price) {
    super(publisherId, version, timestamp);
    this.bookId = bookId;
    this.price = price;
  }

}
