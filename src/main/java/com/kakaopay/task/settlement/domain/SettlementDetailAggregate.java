package com.kakaopay.task.settlement.domain;


import lombok.Getter;
import org.springframework.data.util.Streamable;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class SettlementDetailAggregate implements Streamable<SettlementDetail> {

    private final Streamable<SettlementDetail> streamable;

    @Getter
    private final Map<Long, SettlementDetail> map;

    @Override
    public Iterator<SettlementDetail> iterator() {
        return streamable.iterator();
    }

    private SettlementDetailAggregate(Streamable<SettlementDetail> streamable) {
        this.streamable = streamable;
        this.map = streamable.stream().collect(Collectors.toMap(SettlementDetail::getId, s -> s));
    }

    public static SettlementDetailAggregate of(Streamable<SettlementDetail> streamable) {
        return new SettlementDetailAggregate(streamable);
    }

    public List<SettlementDetail> filterByStatus(SettlementDetailStatus status) {
        return Optional.ofNullable(status)
                .map(s -> stream().filter(detail -> status == detail.getStatus()).toList())
                .orElseGet(this::getList);
    }

    public List<SettlementDetail> getList() {
        return stream().toList();
    }

}
