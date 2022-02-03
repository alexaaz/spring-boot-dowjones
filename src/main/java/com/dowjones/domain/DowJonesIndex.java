package com.dowjones.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="DOWJONES")
public class DowJonesIndex {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	/* quarter:  the yearly quarter (1 = Jan-Mar; 2 = Apr=Jun) */
    private Integer quarter;
    /* stock: the stock symbol */
    private String stock;
    /* date: the last business day of the work (this is typically a Friday) */ 
    private LocalDate date;
    /* open: the price of the stock at the beginning of the week */ 
    private BigDecimal open;
    /* high: the highest price of the stock during the week */ 
    private BigDecimal high;
    /* low: the lowest price of the stock during the week */ 
    private BigDecimal low;
    /* close: the price of the stock at the end of the week */
    private BigDecimal close;
    /* volume: the number of shares of stock that traded hands in the week */
    private Long volume;
    /* percent_change_price: the percentage change in price throughout the week */ 
    private BigDecimal percentChangePrice;
    /* 
     * percent_chagne_volume_over_last_wek: the percentage change in the number of shares of 
		stock that traded hands for this week compared to the previous week
     */
    private BigDecimal percentChangeVolumeOverLastWeek;
    /* previous_weeks_volume: the number of shares of stock that traded hands in the previous week */ 
    private BigDecimal previousWeeksVolume;
    /* next_weeks_open: the opening price of the stock in the following week */ 
    private BigDecimal nextWeeksOpen;
    /* next_weeks_close: the closing price of the stock in the following week */ 
    private BigDecimal nextWeeksClose;
    /* percent_change_next_weeks_price: the percentage change in price of the stock in the 
		following week */
    private BigDecimal percentChangeNextWeeksPrice;
    /* days_to_next_dividend: the number of days until the next dividend */ 
    private BigDecimal daysToNextDividend;
    /* percent_return_next_dividend: the percentage of return on the next dividend */
    private BigDecimal percentReturnNextDividend;
    
    public DowJonesIndex() {};
    
	public DowJonesIndex(Long id, Integer quarter, String stock, LocalDate date, BigDecimal open, BigDecimal high,
			BigDecimal low, BigDecimal close, Long volume, BigDecimal percentChangePrice,
			BigDecimal percentChangeVolumeOverLastWeek, BigDecimal previousWeeksVolume, BigDecimal nextWeeksOpen,
			BigDecimal nextWeeksClose, BigDecimal percentChangeNextWeeksPrice, BigDecimal daysToNextDividend,
			BigDecimal percentReturnNextDividend) {
		super();
		this.id = id;
		this.quarter = quarter;
		this.stock = stock;
		this.date = date;
		this.open = open;
		this.high = high;
		this.low = low;
		this.close = close;
		this.volume = volume;
		this.percentChangePrice = percentChangePrice;
		this.percentChangeVolumeOverLastWeek = percentChangeVolumeOverLastWeek;
		this.previousWeeksVolume = previousWeeksVolume;
		this.nextWeeksOpen = nextWeeksOpen;
		this.nextWeeksClose = nextWeeksClose;
		this.percentChangeNextWeeksPrice = percentChangeNextWeeksPrice;
		this.daysToNextDividend = daysToNextDividend;
		this.percentReturnNextDividend = percentReturnNextDividend;
	}
	
	public DowJonesIndex(Integer quarter, String stock, LocalDate date, BigDecimal open, BigDecimal high, BigDecimal low,
			BigDecimal close, Long volume, BigDecimal percentChangePrice, BigDecimal percentChangeVolumeOverLastWeek,
			BigDecimal previousWeeksVolume, BigDecimal nextWeeksOpen, BigDecimal nextWeeksClose,
			BigDecimal percentChangeNextWeeksPrice, BigDecimal daysToNextDividend,
			BigDecimal percentReturnNextDividend) {
		super();
		this.quarter = quarter;
		this.stock = stock;
		this.date = date;
		this.open = open;
		this.high = high;
		this.low = low;
		this.close = close;
		this.volume = volume;
		this.percentChangePrice = percentChangePrice;
		this.percentChangeVolumeOverLastWeek = percentChangeVolumeOverLastWeek;
		this.previousWeeksVolume = previousWeeksVolume;
		this.nextWeeksOpen = nextWeeksOpen;
		this.nextWeeksClose = nextWeeksClose;
		this.percentChangeNextWeeksPrice = percentChangeNextWeeksPrice;
		this.daysToNextDividend = daysToNextDividend;
		this.percentReturnNextDividend = percentReturnNextDividend;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getQuarter() {
		return quarter;
	}
	public void setQuarter(Integer quarter) {
		this.quarter = quarter;
	}
	public String getStock() {
		return stock;
	}
	public void setStock(String stock) {
		this.stock = stock;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public BigDecimal getOpen() {
		return open;
	}
	public void setOpen(BigDecimal open) {
		this.open = open;
	}
	public BigDecimal getHigh() {
		return high;
	}
	public void setHigh(BigDecimal high) {
		this.high = high;
	}
	public BigDecimal getLow() {
		return low;
	}
	public void setLow(BigDecimal low) {
		this.low = low;
	}
	public BigDecimal getClose() {
		return close;
	}
	public void setClose(BigDecimal close) {
		this.close = close;
	}
	public Long getVolume() {
		return volume;
	}
	public void setVolume(Long volume) {
		this.volume = volume;
	}
	public BigDecimal getPercentChangePrice() {
		return percentChangePrice;
	}
	public void setPercentChangePrice(BigDecimal percentChangePrice) {
		this.percentChangePrice = percentChangePrice;
	}
	public BigDecimal getPercentChangeVolumeOverLastWeek() {
		return percentChangeVolumeOverLastWeek;
	}
	public void setPercentChangeVolumeOverLastWeek(BigDecimal percentChangeVolumeOverLastWeek) {
		this.percentChangeVolumeOverLastWeek = percentChangeVolumeOverLastWeek;
	}
	public BigDecimal getPreviousWeeksVolume() {
		return previousWeeksVolume;
	}
	public void setPreviousWeeksVolume(BigDecimal previousWeeksVolume) {
		this.previousWeeksVolume = previousWeeksVolume;
	}
	public BigDecimal getNextWeeksOpen() {
		return nextWeeksOpen;
	}
	public void setNextWeeksOpen(BigDecimal nextWeeksOpen) {
		this.nextWeeksOpen = nextWeeksOpen;
	}
	public BigDecimal getNextWeeksClose() {
		return nextWeeksClose;
	}
	public void setNextWeeksClose(BigDecimal nextWeeksClose) {
		this.nextWeeksClose = nextWeeksClose;
	}
	public BigDecimal getPercentChangeNextWeeksPrice() {
		return percentChangeNextWeeksPrice;
	}
	public void setPercentChangeNextWeeksPrice(BigDecimal percentChangeNextWeeksPrice) {
		this.percentChangeNextWeeksPrice = percentChangeNextWeeksPrice;
	}
	public BigDecimal getDaysToNextDividend() {
		return daysToNextDividend;
	}
	public void setDaysToNextDividend(BigDecimal daysToNextDividend) {
		this.daysToNextDividend = daysToNextDividend;
	}
	public BigDecimal getPercentReturnNextDividend() {
		return percentReturnNextDividend;
	}
	public void setPercentReturnNextDividend(BigDecimal percentReturnNextDividend) {
		this.percentReturnNextDividend = percentReturnNextDividend;
	}
	
	@Override
	public String toString() {
		return "DowJonesIndexData [id=" + id + ", quarter=" + quarter + ", stock=" + stock + ", date=" + date
				+ ", open=" + open + ", high=" + high + ", low=" + low + ", close=" + close + ", volume=" + volume
				+ ", percentChangePrice=" + percentChangePrice + ", percentChangeVolumeOverLastWeek="
				+ percentChangeVolumeOverLastWeek + ", previousWeeksVolume=" + previousWeeksVolume + ", nextWeeksOpen="
				+ nextWeeksOpen + ", nextWeeksClose=" + nextWeeksClose + ", percentChangeNextWeeksPrice="
				+ percentChangeNextWeeksPrice + ", daysToNextDividend=" + daysToNextDividend
				+ ", percentReturnNextDividend=" + percentReturnNextDividend + "]";
	}
    
}
