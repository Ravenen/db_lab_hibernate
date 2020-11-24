package ua.lviv.iot.hiberlab.model.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;

@Entity
@Table(name = "service", schema = "pavliyk_3")
public class ServiceEntity {
    private Integer id;
    private Date date;
    private BigDecimal durationInHours;
    private BigDecimal totalPriceUah;
    private TerminalEntity terminalByTerminalId;
    private WorkmanEntity workmanByWorkmanId;
    private ServiceTypeEntity serviceTypeByServiceTypeId;

    public ServiceEntity() {
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "date")
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Basic
    @Column(name = "duration_in_hours")
    public BigDecimal getDurationInHours() {
        return durationInHours;
    }

    public void setDurationInHours(BigDecimal durationInHours) {
        this.durationInHours = durationInHours;
    }

    @Basic
    @Column(name = "total_price_uah")
    @GeneratedValue
    public BigDecimal getTotalPriceUah() {
        return totalPriceUah;
    }

    public void setTotalPriceUah(BigDecimal totalPriceUah) {
        this.totalPriceUah = totalPriceUah;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        ServiceEntity that = (ServiceEntity) o;

        if (!id.equals(that.id))
            return false;
        if (!date.equals(that.date))
            return false;
        if (!durationInHours.equals(that.durationInHours))
            return false;
        if (!totalPriceUah.equals(that.totalPriceUah))
            return false;
        if (!terminalByTerminalId.equals(that.terminalByTerminalId))
            return false;
        if (!workmanByWorkmanId.equals(that.workmanByWorkmanId))
            return false;
        return serviceTypeByServiceTypeId.equals(that.serviceTypeByServiceTypeId);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + date.hashCode();
        result = 31 * result + durationInHours.hashCode();
        result = 31 * result + totalPriceUah.hashCode();
        result = 31 * result + terminalByTerminalId.hashCode();
        result = 31 * result + workmanByWorkmanId.hashCode();
        result = 31 * result + serviceTypeByServiceTypeId.hashCode();
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "terminal_id", referencedColumnName = "id", nullable = false)
    public TerminalEntity getTerminalByTerminalId() {
        return terminalByTerminalId;
    }

    public void setTerminalByTerminalId(TerminalEntity terminalByTerminalId) {
        this.terminalByTerminalId = terminalByTerminalId;
    }

    @ManyToOne
    @JoinColumn(name = "workman_id", referencedColumnName = "id", nullable = false)
    public WorkmanEntity getWorkmanByWorkmanId() {
        return workmanByWorkmanId;
    }

    public void setWorkmanByWorkmanId(WorkmanEntity workmanByWorkmanId) {
        this.workmanByWorkmanId = workmanByWorkmanId;
    }

    @ManyToOne
    @JoinColumn(name = "service_type_id", referencedColumnName = "id")
    public ServiceTypeEntity getServiceTypeByServiceTypeId() {
        return serviceTypeByServiceTypeId;
    }

    public void setServiceTypeByServiceTypeId(ServiceTypeEntity serviceTypeByServiceTypeId) {
        this.serviceTypeByServiceTypeId = serviceTypeByServiceTypeId;
    }

    @Override
    public String toString() {
        return "ServiceEntity{" + "id=" + id + ", date=" + date + ", durationInHours=" + durationInHours + ", totalPriceUah=" + totalPriceUah + ", terminalByTerminalId=" + terminalByTerminalId + ", workmanByWorkmanId=" + workmanByWorkmanId + ", serviceTypeByServiceTypeId=" + serviceTypeByServiceTypeId + '}';
    }
}
