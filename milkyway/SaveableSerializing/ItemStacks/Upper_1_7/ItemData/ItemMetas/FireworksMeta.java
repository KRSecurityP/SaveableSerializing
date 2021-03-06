package milkyway.SaveableSerializing.ItemStacks.Upper_1_7.ItemData.ItemMetas;

import milkyway.SaveableSerializing.ItemStacks.Upper_1_7.ItemData.FireworkEffects;
import milkyway.SaveableSerializing.Parser.SaveableData;
import org.bukkit.FireworkEffect;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Developer_Unlocated on 2017-04-18.
 */
public class FireworksMeta extends SubMeta {
    List<FireworkEffects> facts = new ArrayList<>();
    int power = 0;
    @Override
    public void writeBy(StringBuilder  builder) {
        builder.append(String.valueOf(power)).append("\n");
        for(FireworkEffects a : facts)
            a.appendSubSet(builder);
    }

    @Override
    public void appendTo(List<String> builded) {
        try{
            power = Integer.parseInt(builded.get(0));
        }catch (Exception ex){}
    }

    @Override
    public String getName() {
        return "SubMeta-Fireworks";
    }

    @Override
    public void appendObject(String str, SaveableData data) {
        if(data instanceof FireworkEffects)
            facts.add((FireworkEffects) data);
    }

    @Override
    public SaveableData getNewInstance() {
        return new FireworksMeta();
    }

    @Override
    public void setTo(ItemMeta meta) {
        if(meta instanceof FireworkMeta){
            FireworkMeta metas = (FireworkMeta) meta;
            metas.setPower(power);
            for(FireworkEffects a : facts)
            {
                FireworkEffect ef = (FireworkEffect) a.getOriginal();
                if(ef != null)
                    metas.addEffect(ef);
            }
        }
    }

    @Override
    public SubMeta setFrom(ItemMeta meta) {
        if(meta instanceof FireworkMeta){
            FireworkMeta metas = (FireworkMeta) meta;
            power = metas.getPower();
            for(FireworkEffect eff : metas.getEffects())
                facts.add(new FireworkEffects(eff));
        }
        return this;
    }
}
