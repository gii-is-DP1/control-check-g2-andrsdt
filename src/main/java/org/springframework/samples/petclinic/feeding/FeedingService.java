package org.springframework.samples.petclinic.feeding;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FeedingService {

    @Autowired
    private FeedingRepository feedingRepository;

    public List<Feeding> getAll() {
        return this.feedingRepository.findAll();
    }

    public List<FeedingType> getAllFeedingTypes() {
        return this.feedingRepository.findAllFeedingTypes();
    }

    public FeedingType getFeedingType(String typeName) {
        return this.feedingRepository.findByTypeName(typeName);
    }

    @Transactional(rollbackFor = UnfeasibleFeedingException.class)
    public Feeding save(Feeding p) throws UnfeasibleFeedingException {
        if (!p.getFeedingType().getPetType().equals(p.getPet().getType())) {
            throw new UnfeasibleFeedingException();
        }
        return this.feedingRepository.save(p);
    }

}
