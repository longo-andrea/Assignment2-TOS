////////////////////////////////////////////////////////////////////
// [Andrea] [Longo] [1174957]
////////////////////////////////////////////////////////////////////

package it.unipd.tos.business.exception;

public class TakeAwayBillException extends Throwable {
  private String msg;

  public TakeAwayBillException(String msg) {
    this.msg = msg;
  }

  public String getMessage() {
    return msg;
  }
  
}